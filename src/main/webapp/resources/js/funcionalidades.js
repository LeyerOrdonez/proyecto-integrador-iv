inflacion=0.12;
interes=0.19;
miles = new Intl.NumberFormat('es-MX');

function amortizacion(precio,periodo){
n=periodo*-1;

interesReal = ((1+ interes)/(1+inflacion)) -1;
anualidad = precio*((interesReal/(1-((1+interesReal)**n))));
anualidaDecimal = anualidad.toFixed(2);
anualidadConMiles = miles.format(anualidaDecimal);

alert("\nPRECIO DEL PAQUETE: "+miles.format(precio) + "\nCUOTAS MENSUALES: $ " + anualidadConMiles+ "\nPERIODO DE PAGO: " +"DURANTE "+periodo +" meses");
}

function seleccion(identify){
    partes = identify.split("_"); 
    xVisitas= parseInt(partes[0]);
    yDias=parseInt(partes[1]);
    zNoches=parseInt(partes[2]);
    aDesayunos=parseInt(partes[3]);
    bAlmuerzos=parseInt(partes[4]);

    switch (xVisitas) {
        case 1: x=1
            break;
        case 2: x=2
            break;
        case 3: x=3
            break;
        case 4: x=4
            break;
        case 5: x=5
            break;
        case 6: x=6
            break;
        case 7: x=7
            break;
        case 8: x=8
            break;
        case 9: x=9
            break;
        case 10: x=10
            break;
        case 11: x=11
            break;
    }

    switch (yDias) {
        case 1: y=1
            break;
        case 2: y=2
            break;
        case 3: y=3
            break;
        case 4: y=4
            break;
        case 5: y=5
            break;
        case 6: y=6
            break;
        case 7: y=7
            break;
        case 8: y=8
            break;
        case 9: y=9
            break;
        case 10: y=10
            break;
        case 11: y=11
            break;
    }

    switch (zNoches) {
        case 1: z=1
        break;
    case 2: z=2
        break;
    case 3: z=3
        break;
    case 4: z=4
        break;
    case 5: z=5 
        break;
    case 6: z=6
        break;
    case 7: z=7
        break;
    case 8: z=8
        break;
    case 9: z=9
         break;
    case 10: z=10
       break;
    case 11: z=11
    break;
    }

    switch (aDesayunos) {
        case 0: a=0
            break;
        case 1: a=1
            break;
       
    }

    switch (bAlmuerzos) {
        case 0: b=0
        break;
    case 1: b=1
        break;
    }

    var sumatoria=x*50000 + y*100000 + z*120000 + a*20000 + b*15000;
    precio = sumatoria;
    alert("$ "+miles.format(precio) );
   
}


