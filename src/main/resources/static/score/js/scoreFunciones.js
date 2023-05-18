urlBase="http://127.0.0.1:8080/api/Score"

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
    myTable+="<td>messageText</td>";
    myTable+="<td>Stars</td>";
    myTable+="<td>Acción</td>";

    myTable+="</tr>";
    myTable+="</thead>";

    myTable+="<br>";

    for(i=0; i<items.length; i++)
    {   
        myTable+="<tr>";
        myTable+="<td>" +items[i].idScore+"</td>";
        myTable+="<td>" +items[i].messageText+"</td>";
        myTable+="<td>" +items[i].stars+"</td>";
        myTable+="<td> <button class='smallButton' onclick='borrarElemento("+items[i].idScore+")'>Borrar</button> <button class='smallButton' onclick='getOneData("+items[i].idScore+")'>Editar</button>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultado").append(myTable);
}

function guardarInformacion()
{
    let myData={
        idScore:$("#idScore").val(),
        messageText:$("#messageText").val(),
        stars:$("#stars").val(),
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
            $("#idScore").val("");
            $("#messageText").val("");
            $("#stars").val("");
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
        idScore:$("#idScore").val(),
        messageText:$("#messageText").val(),
        stars:$("#stars").val(),
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
            $("#idScore").val("");
            $("#messageText").val("");
            $("#stars").val("");
            traerInformacion(); 
            alert("Se ha actualizado.")
        }
    });
}

function borrarElemento(idElemento)
{
    let myData={
        idScore:idElemento
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
    $("#idScore").val(items[0].idScore);
    $("#messageText").val(items[0].messageText);
    $("#stars").val(items[0].stars);
}