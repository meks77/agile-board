# agile-board
Agile Board for agile Teams

## Vision
As a developer I'd like to have a board for my iterations, which looks behaves like a board with sticky notes in the office.

The main fokus is the iteration(sprint) board. It should look similar to:

This is just a very simple draft of the iteration board.

```mermaid
block-beta
    columns 4
    space TODO InProgress Done
    
    Story1("Story 1")
    block:todoStory1
        Story1Task2("Task 2") Story1Task3("Task 3") 
    end
    Story1Task1("Task 1")  
    space

    Story2("Story 2")
    block:todoStory2
        Story2Task2("Task 2") Story2Task3("Task 3") Story2Task4("Task 4")
    end
    space
    Story2Task1("Task 1")

    Story3("Story 3")
    block:todoStory3
        Story3Task1("Task 1") Story3Task3("Task 2") Story3Task3("Task 3")
    end
    space
    space

    Story4("Story 4")
    block:todoStory4
        Story4Task1("Task 1") Story4Task2("Task 2") Story4Task3("Task 3")
    end
    space
    space

    Story5("Story 5")
    block:todoStory5
        Story5Task1("Task 1") Story5Task2("Task 2") Story5Task3("Task 3")
    end
    space
    space
```

## The problem
There are many solutions for agile boards. 
Many are too complex or have to many restrictions to developers.

## The wish list

as a developer I'd like to

* manage the stories and the tasks of a story like with post its
* create simple and fast the tasks for a story
* decide which columns the board has
* have issues of different products/projects on one board
* have the possibility to import and synchronize the content of the sprint with a 3rd party solution(e.g. Jira)
* manage the products backlog for a board, if it is not synchronized with a 3rd party solution(e.g. Jira)

## Documentation
The documentation of the project can be found [here](documentation/index)