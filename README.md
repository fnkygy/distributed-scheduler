# Distributed Scheduler

# Problem Statement
Design a distributed task scheduler capable of executing long running tasks by dividing them into multiple smaller unit of work and executing them parallely on multiple nodes.
- Scheduler must be capable of the following:
  - Distributed - Ability to run on multiple nodes
  - Fault tolerant - If a node goes down, the jobs should not be impacted and must resume from the last known checkpoint.
  - Parallel - The scheduler must have ability to run multiple jobs at the same time with minimal wait time.
  - Horizontal scale - The scheduler must be capable of scaling up or scaling down horizontally and the same be implemented gracefully (especially in case of scale down)

- Task - is typically a long running unit of work that could span minutes or hours.
  - Master Task
    - The master task can either be scheduled or invoked ad-hoc.
    - The master task will be created based on a template that defines the skeleton of the work to be done.
    - When the master task is invoked, the caller will provide appropriate variables to be replaced in the placeholders of the template to form an instance of the master task.
    - The scheduler must ensure that master task is triggered exactly once.
    - The role of the master task is to simply resolve placeholders and the implementation should have a strategy to reduce the task into manageable units of work that can be picked up by the Child Task.
    - The master task must create Child Task(s) with required task data required to execute them.

  - Child Task
    - The Child Task is the smaller & manageable unit of work that can be executed parallely. The master task must ensure that child task are not interleaved & can truly run parallely without requirement of any synchronization.
    - Once all child tasks have finished execution, the scheduler must ensure that master task has also been marked as success/failure.
  
# Implementation
- Preferable language - Java
- The implementation must follow OO best practices in the industry.
- Whenever required, please stub complex logic with comments supporting your assumptions.
- Sample master task - can be to print numbers from 1 to n (n is the template variable that is provided by the invoker when creating the master task)
- Sample child task - The child task must print numbers from a to b, which are divided and provided for by the sample master task.
