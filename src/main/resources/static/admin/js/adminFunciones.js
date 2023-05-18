urlBase="http://127.0.0.1:8080/api/Admin"

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
    myTable+="<td>Name</td>";
    myTable+="<td>Email</td>";
    myTable+="<td>Password</td>";
    myTable+="<td>Acción</td>";

    myTable+="</tr>";
    myTable+="</thead>";

    myTable+="<br>";

    for(i=0; i<items.length; i++)
    {   
        myTable+="<tr>";
        myTable+="<td>" +items[i].idAdmin+"</td>";
        myTable+="<td>" +items[i].name+"</td>";
        myTable+="<td>" +items[i].email+"</td>";
        myTable+="<td>" +items[i].password+"</td>";
        myTable+="<td> <button class='smallButton' onclick='borrarElemento("+items[i].idAdmin+")'>Borrar</button> <button class='smallButton' onclick='getOneData("+items[i].idAdmin+")'>Editar</button>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultado").append(myTable);
}

function guardarInformacion()
{
    let myData={
        idAdmin:$("#idAdmin").val(),
        name:$("#name").val(),
        email:$("#email").val(),
        password:$("#password").val(),
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
            $("#idAdmin").val("");
            $("#name").val("");
            $("#email").val("");
            $("#password").val("");
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
        idAdmin:$("#idAdmin").val(),
        name:$("#name").val(),
        email:$("#email").val(),
        password:$("#password").val(),
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
            $("#idAdmin").val("");
            $("#name").val("");
            $("#email").val("");
            $("#password").val("");
            traerInformacion(); 
            alert("Se ha actualizado.")
        }
    });
}

function borrarElemento(idElemento)
{
    let myData={
        idAdmin:idElemento
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
    $("#idAdmin").val(items[0].idAdmin);
    $("#name").val(items[0].name);
    $("#email").val(items[0].email);
    $("#password").val(items[0].password);
}