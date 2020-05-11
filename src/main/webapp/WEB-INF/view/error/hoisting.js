class User{
    constructor(name, interests) {
        this.name = name;
        this.interests = interests;
    }
    greeting(){
        console.log('Hi I am ' + this.name);
    }
    get interestsCount(){
        return this.interests ? this.interests.length : 0;
    }
}
class TeamMember extends User{
    constructor(name, interests) {
        super(name, interests);
        this._tasks = [];
        this.welcomeText = 'Welcome to the team!';
    }
    greeting() {
        console.log('I am ' + this.name + ". " + this.welcomeText);
    }
    work(){
        console.log('I\'m working on ' + this._tasks.length + ' tasks');
    }
    set tasks(tasks){
        let acceptedTasks = [];
        if(tasks.length > TeamMember.maxTasksCapacity()){
            acceptedTasks = tasks.slice(0, TeamMember.maxTasksCapacity());
            console.log('It\'s over max capacity. Can only take two.');
        }else{
            acceptedTasks = tasks;
        }
        this._tasks = this._tasks.concat(acceptedTasks);
    }
    static maxTasksCapacity(){
        return 2;
    }
}
let member = new TeamMember('Han', ['Web']);
member.greeting();
member.tasks = ['Coding', 'Study', 'Drink coffee'];
member.work();
console.log(member.interestsCount);
member.interestsCount = 2;
console.log(member.interestsCount);
console.log(member.tasks);