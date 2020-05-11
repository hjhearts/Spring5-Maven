import User from './user.js';
import * as Roles from './role.js';
import completeTask from "./task.js";
import {completedCount} from "./task.js";

let user = new User('Ted', Roles.USER);
completeTask(user);
console.log(`Total completed ${completedCount}`);
User.prototype.walk = function(){
    console.log(`${this.name} walks`);
};
user.walk();