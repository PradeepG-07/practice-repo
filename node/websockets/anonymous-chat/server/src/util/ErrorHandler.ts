import type WebSocket from "ws";
import MessageError from "./MessageError.js";
import { MessageType } from "./MessageTypeEnum.js";

export default function errorHandler(socket: WebSocket, cb: Function){
    try {
        cb();
    } catch (error) {
        if(error instanceof SyntaxError){
            const message = JSON.stringify({"type": MessageType.INVALID_MESSAGE_FORMAT, "message": "Invalid JSON"});
            return socket.send(message);
        }
        else if(error instanceof MessageError){
            const message = JSON.stringify({"type": error.getType(), "message": error.getMessage()});
            return socket.send(message);
        }
        else{
            const message = JSON.stringify({"type": MessageType.UNKNOWN_ERROR, "message": "Unknown Error Occurred."});
            return socket.send(message);
        }
    }
}