# Android Simple To Do Application

Simple To Do Application with Room Database

## Used Libraries
- androidx.room:room-runtime
- androidx.room:room-compiler
- androidx.swiperefreshlayout:swiperefreshlayout


## Screenshoot
<img src="https://github.com/ramazanogunc/Android-Simple-ToDo/blob/master/ss/screen1.png"
width="210"
alt="Android Simple To Do App 1"/>
<img src="https://github.com/ramazanogunc/Android-Simple-ToDo/blob/master/ss/screen2.png"
width="210"
alt="Android Simple To Do App 2"/>


## Activity ve Fragments
- Tab Activity: TabLayout Activity
 - ToDo Fragment: ToDo List , insert, delete to do task fragment
 - Done Fragment: Completed task List and delete fragment

## Model( Entity) Classes
- Task

## Database Classes
- AppDatabbase: Abstract Room database
- TaskDao: Room Database Query interface
- DatabaseClient: Room database init and singleton database client class.

## License
[APACHE](http://www.apache.org/licenses/LICENSE-2.0)
