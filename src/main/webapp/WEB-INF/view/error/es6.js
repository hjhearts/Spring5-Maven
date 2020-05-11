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

const advice = 'Stay hungry, stay foolish';

let advisor = {
    __proto__: new TeamMember('Adam', ['Consulting']),
    advice,
    greeting(){
        super.greeting();
        console.log(this.advice);
    },
    [advice.split('.')[0]] : 'Always learn more'
};

console.log(TeamMember.prototype.isPrototypeOf(advisor));
console.log(advisor instanceof TeamMember);
advisor.greeting();
