urlBase="http://127.0.0.1:8080/api/Gama"

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

    myTable+="<td>IdGama</td>";
    myTable+="<td>Name</td>";
    myTable+="<td>Description</td>";
    myTable+="<td>Acción</td>";

    myTable+="</tr>";
    myTable+="</thead>";

    myTable+="<br>";

    for(i=0; i<=items.length; i++)
    {   
        myTable+="<tr>";
        myTable+="<td>" +items[i].idGama+"</td>";
        myTable+="<td>" +items[i].name+"</td>";
        myTable+="<td>" +items[i].description+"</td>";  
        myTable+="<td> <button class='smallButton' onclick='borrarElemento("+items[i].idGama+")'>Borrar</button> <button class='smallButton' onclick='getOneData("+items[i].idGama+")'>Editar</button>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultado").append(myTable);
}

function guardarInformacion()
{
    let myData={
        idGama:$("#idGama").val(),
        name:$("#name").val(),
        description:$("#description").val(),
    };

    let dataToSend=JSON.stringify(myData);
    $.ajax(
    {
        url: urlBase + "/save",
        type: "POST",
        data:myData,
        datatype:"JSON",        
        success:function(respuesta)
        {
            $("#resultado").empty();     
            $("#idGama").val("");
            $("#name").val("");
            $("#description").val("");
            traerInformacion();  
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
        idGama:$("#idGama").val(),
        name:$("#name").val(),
        description:$("#description").val(),
    };
    let dataToSend=JSON.stringify(myData);

    $.ajax(
    {
        url:urlBase + "/update",
        type:"PUT",
        data:dataToSend,
        contentType:"application/JSON", 
        datatype:"JSON",
        success:function(respuesta)
        {
            $("#resultado").empty(); 
            $("#idGama").val("");
            $("#name").val("");
            $("#description").val("");
            traerInformacion(); 
            alert("Se ha actualizado.")
        }
    });
}

function borrarElemento(idGamaElemento)
{
    let myData={
        idGama:idGamaElemento
    };

    let dataToSend=JSON.stringify(myData);
    $.ajax(
    {
        url:urlBase +"/" + idGamaElemento,
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

function getOneData(idGamaaEditar)
{
    $.ajax(
        {
            url:urlBase+"/"+idGamaaEditar,
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
    $("#idGama").val(items[0].idGama);
    $("#name").val(items[0].name);
    $("#description").val(items[0].description);
}