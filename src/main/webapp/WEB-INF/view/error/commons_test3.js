function bookHotel(city){
    var availableHotel = 'None';
    for (var i = 0; i < hotels.length; i++) {
        var hotel = hotels[i];
        if(hotel.city === city && hotel.hasRoom){
            availableHotel = hotel.name;
            break;
        }
    }

    console.log('Checked ' + (i+1) + ' record(s)');
    console.log('Last Checked ' + hotel.name);
    {
        function placeOrder(){
            var totalAmount = 200;
            console.log('Order placed to ' + availableHotel);
        }
    }
    placeOrder();
    return availableHotel;
}
var hotels = [
    {
        name : 'Hotel A',
        hasRoom : false,
        city : 'Seoul'
    },
    {
        name : 'Hotel B',
        hasRoom: true,
        city : "Seoul"
    }
];
console.log(bookHotel('Seoul'));