urlBase="http://127.0.0.1:8080/api/Reservation"

function traerInformacion()    
{   
    $.ajax(
    {
        url:urlBase + "/all",
        type:"GET",
        datatype:"JSON", 

        success:function(respuesta)     
        {
            borrarTabla();
            console.log(respuesta);
            pintarRespuesta(respuesta.items); 
        }
    });
}

function pintarRespuesta(items)
{
    let myTable="<table border=1>";
    myTable+="<thead>";
    myTable+="<tr>";

    myTable+="<td>Id</td>";
    myTable+="<td>startDate</td>";
    myTable+="<td>devolutionDate</td>";
    myTable+="<td>Status</td>";
    myTable+="<td>Acción</td>";

    myTable+="</tr>";
    myTable+="</thead>";

    myTable+="<br>";

    for(i=0; i<items.length; i++)
    {   
        myTable+="<tr>";
        myTable+="<td>" +items[i].idReservation+"</td>";
        myTable+="<td>" +items[i].startDate+"</td>";
        myTable+="<td>" +items[i].devolutionDate+"</td>";
        myTable+="<td>" +items[i].status+"</td>";
        myTable+="<td> <button class='smallButton' onclick='borrarElemento("+items[i].idReservation+")'>Borrar</button> <button class='smallButton' onclick='getOneData("+items[i].idReservation+")'>Editar</button>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultado").append(myTable);
}

function guardarInformacion()
{
    let myData={
        idReservation:$("#idReservation").val(),
        startDate:$("#startDate").val(),
        devolutionDate:$("#devolutionDate").val(),
        status:$("#status").val(),
    };

    let dataToSend=JSON.stringify(myData);
    $.ajax(
    {
        url: urlBase + "/save",
        type: "POST",
        data:myData,
        datatype:"JSON",        //Lo que estoy esperando
        success:function(respuesta)
        {
            $("#resultado").empty();      //Borra la tabla
            $("#idReservation").val("");
            $("#startDate").val("");
            $("#devolutionDate").val("");
            $("#status").val("");
            traerInformacion();     //Trae toda la tabla de nuevo
            alert("Se ha guardado.")
        }
    })

}

function borrarTabla()
{
    $("#resultado").empty();
}

function editarInformacion()
{
    let myData={
        idReservation:$("#idReservation").val(),
        startDate:$("#startDate").val(),
        devolutionDate:$("#devolutionDate").val(),
        status:$("#status").val(),
    };
    let dataToSend=JSON.stringify(myData);

    $.ajax(
    {
        url:urlBase + "/update",
        type:"PUT",
        data:dataToSend,
        contentType:"application/JSON", //Le digo al servidor en qué formato le estoy enviando la información
        datatype:"JSON",
        success:function(respuesta)
        {
            $("#resultado").empty(); 
            $("#idReservation").val("");
            $("#startDate").val("");
            $("#devolutionDate").val("");
            $("#status").val("");
            traerInformacion(); 
            alert("Se ha actualizado.")
        }
    });
}

function borrarElemento(idElemento)
{
    let myData={
        idReservation:idElemento
    };

    let dataToSend=JSON.stringify(myData);
    $.ajax(
    {
        url:urlBase +"/" + idElemento,
        type:"DELETE",
        data:dataToSend,
        contentType:"application/JSON",
        datatype:"JSON",
        success:function(respuesta)
        {
            $("#resultado").empty();
            traerInformacion();
            alert("Se ha Eliminado.")
        }
    })
}

function getOneData(idaEditar)
{
    $.ajax(
        {
            url:urlBase+"/"+idaEditar,
            type:"GET",
            datatype:"JSON", 
    
            success:function(respuesta)    
            {
                borrarTabla();
                screentoModify(respuesta.items); 
            }
        });
}

function screentoModify(items)
{
    $("#idReservation").val(items[0].idReservation);
    $("#startDate").val(items[0].startDate);
    $("#devolutionDate").val(items[0].devolutionDate);
    $("#status").val(items[0].status);
}