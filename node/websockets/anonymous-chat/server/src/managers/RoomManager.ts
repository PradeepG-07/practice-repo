import Room from "../models/Room.js";
import type Member from "../models/Member.js";
import MessageError from "../util/MessageError.js";
import { MessageType } from "../util/MessageTypeEnum.js";

export default class RoomManager{
    private rooms: Map<String, Room>;

    constructor(){
        this.rooms = new Map<String, Room>();
    }

    joinRoom(roomId: string, member: Member){
        if(!this.rooms.has(roomId)){
            this.rooms.set(roomId, new Room(roomId));
        }
        
        this.rooms.get(roomId)!.addMember(member);
        member.setRoomId(roomId);

        const message = JSON.stringify({type: MessageType.ROOM_JOINED, data: {memberId: member.getId()}, message: "Joined room."})
        member.getSocket().send(message);
    }

    exitRoom(roomId: string, memberId: string){
        const room = this.rooms.get(roomId);
        if(room) room.removeMember(memberId);
    }

    publishMessage(roomId: string, message: string, fromMemberId: string){
        const room = this.rooms.get(roomId);
        if(!room) throw new MessageError(MessageType.INVALID_ROOM, "Room does not exist.");

        const memberPartOfRoom = room.hasMember(fromMemberId);
        if(!memberPartOfRoom) throw new MessageError(MessageType.UNAUTHORIZED, "Please join the room to send messages.");

        const fromMember = room.getMember(fromMemberId)!;
        room.publishMessage(message, fromMember);
    }
}