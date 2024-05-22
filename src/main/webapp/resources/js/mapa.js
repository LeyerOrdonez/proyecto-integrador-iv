var mymap = L.map('mapid').setView([51.505, -0.09], 13);
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
}).addTo(mymap);

var marker = L.marker([51.5, -0.09]).addTo(mymap);
var marker2 = L.marker([51.5, -1]).addTo(mymap);
var marker3 = L.marker([53, -0.09]).addTo(mymap);