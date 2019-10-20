# Distributed Scheduler

## Problem Statement
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
  
## Implementation
- Preferable language - Java
- The implementation must follow OO best practices in the industry.
- Whenever required, please stub complex logic with comments supporting your assumptions.
- Sample master task - can be to print numbers from 1 to n (n is the template variable that is provided by the invoker when creating the master task)
- Sample child task - The child task must print numbers from a to b, which are divided and provided for by the sample master task.

## Solution
### Architecture and Design
<img src="https://drive.google.com/uc?export=view&id=176TWpTWohN8uM7M4l_s1FZi0sHkbmltq" width="75%" height="75%"/>

##### Open-Close Principle
One of the main pillars on which this solution is built is the open-close principle. All logical entities are segregated by interfaces.

##### Scheduler
This is the main interface for the user. The user sees the scheduler having:
- A user interface to provide user management and visibility.
- The ability to maintain more than one registry for task execution.
- Manages the tasks against the registries.
- Provides the ability for the user to schedule one-time or regular jobs.

<img src="https://drive.google.com/uc?export=view&id=1z5E7pdqvfosx4aNVoaOVNB0e3fFP7BvL" width="15%" height="15%"/>

##### Node
This is the basic building block of an executor. A node has two possible operations:
- The ability to distribute tasks among other nodes and itself (execute a master job).
- The ability to execute smaller tasks delegated by other masters and itself (execute a child job).

Each node is independent, but communicates with other nodes through an embedded hazelcast grid. For the sake of simplicity, the nodes in the demo provided here communicate via interfaces in a single JVM. Master tasks and child tasks are evenly distributed among the nodes, and this is managed by the Registry.

##### Registry
The next bigger block is the Registry. This is the 'manager' of the nodes, and it takes care of:
- Distribution of master and child tasks evenly across the cluster of nodes.
- HA for every task, ensuring that other nodes can take over in case of failures.
- Regular health checks to ensure node sanity.
- Graceful bringup and shutdown of nodes.

##### Task
The task is one more building block in the scheduler. It is an abstract base class extended by two classes:
- AbstractMasterTask
- AbstractChildTask

These two classes are the ones that will be exposed as a library to developers, so that they can develop their own tasks by adhering to the master-child principle.

<img src="https://drive.google.com/uc?export=view&id=1l5WwXRyh5QeECBoHJ0TDmkwaTxA_tZih" width="50%" height="50%"/>

#### Tech Stack
##### Embedded Hazelcast for node management
Hazelcast is an excellent in-memory platform to provide shared data across multiple nodes. This will be heavily used by the Registry to maintain shared data structures across nodes.

##### Embedded Hazelcast for inter-node communication
Whether it's synchronous or asynchronous communication (such as job status updates) across nodes, Hazelcast provides the features out of the box for inter-node communication.

##### Cassandra as the data store
The main reason for choosing Cassandra here are two fold:
- It will serve as the disk storage for all the hazelcast data. In case hazelcast is down, Cassandra will be used as the backup to bring it up again.
- It is a no-SQL datastore perfect for storing job templates, user management, job statuses, etc.

##### Spring Boot for microservice deployment
I've personally used Dropwizard extensively, but for this use case I've seen that the support for Hazelcast is better on Spring Boot than on Dropwizard. Hence the choice.

#### Simplification for the demo
For the sake of a demo, the implementation and tech stack has been brought down into:
- Deployment: All logically-separate components are segregated by interfaces rather than separate spring boot applications. It will all run in one single JVM.
- Node management: There is no hazelcast used for communication, it will be an in-memory interface-to-interface communication.
- Job management and storage: There is no storage, the DAO is a dummy implementation.

##### Testing
There are two test cases in the repository, which cover:
- A successful task distribution and execution scenario
- A failure case where one of the child tasks fail, leading to the rest being successful but the overall master task failing.

Please run "mvn test" to execute the test cases, preferably using "IntelliJ Idea CE".
