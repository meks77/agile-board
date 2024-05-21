Test of plantum in MD
```plantuml
@startsalt
skinparam handwritten true
{#
    Story | Todo | In Progress | Done
    [Story 1] | {[Task 1] | [Task 2] | [Task 3]} | [Task 4] | [Task 5]
    [Story 1] | [Task 1] | [Task 2] | .
    [Story 1] | [Task 1] | [Task 2]  | [Task 3]
}
@endsalt
```