import {Component} from '@angular/core';
import {CommonModule, NgForOf} from "@angular/common";
import {
  CdkDrag,
  CdkDragDrop,
  CdkDragEnd,
  CdkDragEnter,
  CdkDragStart,
  CdkDropList,
  CdkDropListGroup
} from "@angular/cdk/drag-drop";

interface BoardColumn {
  id: string;
  title: string;
}

interface Story {
  id: number;
  title: string;
  tasks: Task[];
  blocked?: boolean;
}

interface Task {
  id: number;
  title: string;
  status?: string; // Optional status property for future enhancements
}

@Component({
  selector: 'app-board',
  standalone: true,
  imports: [
    NgForOf,
    CommonModule,
    CdkDrag,
    CdkDropList,
    CdkDropListGroup
  ],
  templateUrl: './board.component.html',
  styleUrl: './board.component.scss'
})
export class BoardComponent {

  columns: BoardColumn[] = [
    {id: 'TODO', title: 'Todo'},
    {id: 'IN_PROGRESS', title: 'In Progress'},
    {id: 'DONE', title: 'Done'},
  ]

  stories: Story[] = [
    {
      id: 1,
      title: 'Story 1',
      tasks: [
        {id: 1, title: 'Implement', status: 'DONE'},
        {id: 2, title: 'Test', status: 'IN_PROGRESS'},
        {id: 3, title: 'Review', status: 'IN_PROGRESS'},
      ]
    },
    {
      id: 2,
      title: 'Story 2',
      tasks: [
        {id: 4, title: 'Implement', status: 'IN_PROGRESS'},
        {id: 5, title: 'Test', status: 'TODO'},
        {id: 6, title: 'Review', status: 'TODO'},
      ]
    },
    {
      id: 3,
      title: 'Story 3',
      tasks: [
        {id: 7, title: 'Implement', status: 'IN_PROGRESS'},
        {id: 8, title: 'Test', status: 'TODO'},
        {id: 8, title: 'Review', status: 'TODO'},
        {id: 8, title: 'Merge Hotfix', status: 'TODO'},
      ]
    },
    {
      id: 4,
      title: 'Story 4',
      tasks: [
        {id: 7, title: 'Implement', status: 'TODO'},
        {id: 8, title: 'Test', status: 'TODO'},
        {id: 8, title: 'Review', status: 'TODO'},
        {id: 8, title: 'Merge Hotfix', status: 'TODO'},
      ]
    }
  ];

  statuses = [
    {name: 'TODO'},
    {name: 'IN_PROGRESS'},
    {name: 'FINISHED'},
  ];

  private dragging: Task | undefined;

  tasksForColumn(story: Story, column: BoardColumn) {
    return story.tasks.filter((task: Task) => task.status === column.id);
  }

  trackByTask(index: number, task: Task) {
    return task.title; // Track tasks by title for change detection
  }

  handleDragStart(event: DragEvent, task: Task) {
    console.log("dragging started for", task);
    this.dragging = task;
    if (event.dataTransfer != null) {
      event.dataTransfer.setData('task', JSON.stringify(task));
      event.dataTransfer.dropEffect = 'move';
      event.dataTransfer.effectAllowed = 'move';
    }
  }

  handleDragEnd(event: DragEvent) {

    // Update the task's status based on the drop location
    if (this.dragging != undefined && event.target && event.target instanceof HTMLElement) {
      console.log("setting status of ", this.dragging);
      this.dragging.status = this.getStatusFromElement(event.target)
    }
    this.dragging = undefined;
  }

  dragStart(event: CdkDragStart, task: Task) {
    console.log("dragging of task", task.title, "started");
    this.dragging = task;
  }


  dragEnd(event: CdkDragEnd<string[]>) {
    console.log("dragEnd", event);
    console.log("element", event.source.element.nativeElement);
    // if (this.dragging != undefined) {
    //   let statusFromElement = this.getStatusFromElement(event.source.element.nativeElement);
    //   console.log("Status from target", statusFromElement);
    //   this.dragging.status = statusFromElement;
    // }
    // this.dragging = undefined;
  }

  getStatusFromElement(element: HTMLElement): string {
    let parent = element;
    while (parent && !parent.classList.contains('status-column')) {
      if (parent.parentElement) {
        parent = parent.parentElement;
      }
    }
    if (parent) {
      console.log("parent is ", parent)
      let elementColumnId = parent.querySelector('.status-column');
      console.log("id of column: ", parent.id);
      let newStatus = parent.id.split("-").at(0);
      console.log("status of column: ", newStatus)
      if (newStatus) {

        return newStatus;
      }
    }
    console.log("no status of column found: ", parent)
    return '';
  }

  dragEntered(event: CdkDragEnter<any>) {

  }

  drop(event: CdkDragDrop<Task[], Task>, column: BoardColumn) {
    if (this.dragging != undefined) {
      console.log("drop of task", this.dragging);
      let statusFromElement = this.getStatusFromElement(event.container.element.nativeElement);
      console.log("Status from target", statusFromElement);
      this.dragging.status = statusFromElement;
    }
    this.dragging = undefined;
  }
}
