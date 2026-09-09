import { WebSocketServer } from "ws";
import SocketManager from "./managers/SocketManager.js";
import RoomManager from "./managers/RoomManager.js";

const wss = new WebSocketServer({
    port: 8888
});

const roomManager = new RoomManager();
const socketManger = new SocketManager(roomManager);

wss.on("connection", (socket) => {
    socketManger.initHandlers(socket);
});

wss.on("close", () => {
    console.log("Closing server...")
});
