function User(name, department){
    var _department = department;
    var _name = name;
    Object.defineProperty(this, 'name', {
        value : _name,
        writable : true,
        enumerable : true,
        configurable : false
    });
    Object.defineProperty(this, 'department', {
        get : function(){
            console.log('Retrieving department');
            return _department;
        },
        set : function(newValue){
            console.log('Updating department value to ' + newValue);
            _department = newValue;
        },
        enumerable : true,
        configurable : true
    });
    Object.defineProperty(this, 'greeting', {
        value : function(){
            console.log('Hi my name is ' + _name);
        },
        enumerable : false,
        configurable : false
    });
}
var user = new User('Han', "Engineering");
console.log(user.department);
user.department = 'Marketing';
user.greeting();
Object.defineProperty(User, 'name', {
    enumerable : false
});
try {
    delete user.name;
}catch(e){
    console.log(e.message);
}
delete user.department;
for(var prop in user){
    console.log(prop);
}