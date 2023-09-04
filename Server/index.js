var app = require ('express')();
var server = require('http').Server(app);
var io = require ('socket.io')(server);

server.listen(8080,function(){
    console.log("Servidor está rodando...");
});

io.on('connection',function(socket){
        //jogador conectado
    console.log("Jogador conectado!");
    socket.emit('socketID', {id:socket.id})
    socket.broadcast.emit('newPlayer',{id:socket.id}) 
    socket.on('disconnect',function(){
        //Jogador desconectou
        console.log("Jogador desconectado");
    });
}); 