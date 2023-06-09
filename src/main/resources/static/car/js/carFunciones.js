urlBase="http://127.0.0.1:8080/api/Car"

function traerInformacion()      //Agregamos un metodo
{   
    $.ajax(
    {
        url:urlBase + "/all",
        type:"GET",
        datatype:"JSON", //corresponde al tipo de dato que esperamos que el sevidor nos entregue

        success:function(respuesta)     //Acá se puede validar la respuesta.
        {
            borrarTabla();
            console.log(respuesta);
            /*
            for(i=0; i<respuesta.items.length; i++)
            {
                $("#resultado").append(respuesta.items[i].name+"<br>");  //mostramos el "name" con un ciclo
            }
            */
            pintarRespuesta(respuesta.items); //Agregamos la funcion con la tabla
        }
    });
}

function pintarRespuesta(items)
{
    let myTable="<table border=1>";
    myTable+="<thead>";
    myTable+="<tr>";

    myTable+="<td>IdCar</td>";
    myTable+="<td>Name</td>";
    myTable+="<td>Brand</td>";
    myTable+="<td>Description</td>";
    myTable+="<td>Acción</td>";

    myTable+="</tr>";
    myTable+="</thead>";

    myTable+="<br>";

    for(i=0; i<items.length; i++)
    {   
        myTable+="<tr>";
        myTable+="<td>" +items[i].idCar+"</td>";
        myTable+="<td>" +items[i].name+"</td>";
        myTable+="<td>" +items[i].brand+"</td>";
        myTable+="<td>" +items[i].description+"</td>";
        myTable+="<td> <button class='smallButton' onclick='borrarElemento("+items[i].idCar+")'>Borrar</button> <button class='smallButton' onclick='getOneData("+items[i].idCar+")'>Editar</button>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultado").append(myTable);
}

function guardarInformacion()
{
    let myData={
        idCar:$("#idCar").val(),
        name:$("#name").val(),
        brand:$("#brand").val(),
        description:$("#description").val(),
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
            $("#idCar").val("");
            $("#name").val("");
            $("#brand").val("");
            $("#description").val("");
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
        idCar:$("#idCar").val(),
        name:$("#name").val(),
        brand:$("#brand").val(),
        description:$("#description").val(),
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
            $("#idCar").val("");
            $("#name").val("");
            $("#brand").val("");
            $("#description").val("");
            traerInformacion(); 
            alert("Se ha actualizado.")
        }
    });
}

function borrarElemento(idElemento)
{
    let myData={
        idCar:idElemento
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
    $("#idCar").val(items[0].idCar);
    $("#name").val(items[0].name);
    $("#brand").val(items[0].brand);
    $("#description").val(items[0].description);
}