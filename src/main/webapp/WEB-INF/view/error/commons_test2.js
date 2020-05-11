function User(name, interests){
    this.name = name;
    this.interests = interests;
}
User.prototype.greeting = function(){
    console.log('hi my name is ' + this.name);
};
function TeamMember(name, interests, tasks){
    User.call(this, name, interests);
    this.tasks = tasks;
}
TeamMember.prototype = Object.create(User.prototype);
TeamMember.prototype.greeting = function(){
    console.log('Hi ' + this.name + ' Welcome to team');
};
TeamMember.prototype.work = function(){
    console.log('I\'m working on ' + this.tasks.length + ' tasks');
};
var member = new TeamMember('Han', ['java'], ['coding', 'study']);

member.greeting();
member.work();

console.log(member instanceof TeamMember);
console.log(member instanceof User);
console.log(member instanceof Object);

User.prototype.eat = function(){
    console.log('what will i have for lunch?');
};
member.eat();

Object.prototype.move = function(){
    console.log('Every object can move now');
};
member.move();
var alien = {};
alien.move();
User.move();