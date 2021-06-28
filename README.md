Sample URL :

Login : POST http://localhost:8080/login

For Student : //can only view details
{
"username":"Student",
"password":"admin"
}

For Instructor : //can view/create/edit and delete details
{
"username":"Instructor",
"password":"admin"
}

Copy Auth token and append to your requests now.

tags api :
create Tag : POST http://localhost:8080/create/tag
{
"name": "Demo",
"description": "Sample Tag for Tag2"
}

view Tags : GET http://localhost:8080/tags

edit Tag : PUT http://localhost:8080/edit/tag/2

    {
        "name": "DEmo",
        "description": "Sample Tag for Tag2"
    }

[Architecture](arch.png)