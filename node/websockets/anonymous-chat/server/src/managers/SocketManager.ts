import WebSocket from "ws"
import RoomManager from "./RoomManager.js";
import errorHandler from "../util/ErrorHandler.js";
import MessageError from "../util/MessageError.js";
import { MessageType } from "../util/MessageTypeEnum.js";
import Member from "../models/Member.js";

export default class SocketManager{
    private roomManager: RoomManager;

    constructor(roomManager: RoomManager){
        this.roomManager = roomManager;
    }

    initHandlers(socket: WebSocket){
        const member = new Member(socket);
        socket.on("message", (data, isBinary) => this.onMessage(member, data, isBinary));
        socket.on("close", () => this.onClose(member));        
        socket.on("pong", () => member.setIsAlive(true));
    }

    private onMessage(member: Member, data: WebSocket.RawData, isBinary: boolean){
        errorHandler(member.getSocket(), () => {
            const memberId = member.getId();

            if(isBinary) 
                throw new MessageError(MessageType.INVALID_MESSAGE_FORMAT, "Supports only text messages.");
    
            const parsedData = JSON.parse(data.toString());
            const type = parsedData.type;
    
            if(type == MessageType.JOIN_ROOM){
                const {roomId, name} = parsedData;

                if(!roomId || !name) 
                    throw new MessageError(MessageType.MISSING_PARAMS, "Room Id and Name are required.");
                
                member.setName(parsedData.name);
                this.roomManager.joinRoom(parsedData.roomId, member);
            }

            if(type == MessageType.SEND_MESSAGE){
                const {roomId, message} = parsedData;

                if(!roomId || !message) 
                    throw new MessageError(MessageType.MISSING_PARAMS, "Room Id and Message are required.");

                this.roomManager.publishMessage(roomId, message, memberId);
            }
        });
    }
    
    private onClose(member: Member){
        this.roomManager.exitRoom(member.getId(), member.getRoomId());
    }
}