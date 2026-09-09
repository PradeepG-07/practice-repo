import { MessageType } from "../util/MessageTypeEnum.js";
import Member from "./Member.js";

export default class Room{
    private roomId: string;
    private members: Map<String, Member>;

    constructor(roomId: string){
        this.roomId = roomId;
        this.members = new Map<String, Member>();

        setInterval(() => {
            this.members.forEach((member) => {
                const socket = member.getSocket();
                if(!member.getIsAlive()){
                    this.removeMember(member.getId());
                    socket.terminate();
                    return ;
                }
                member.setIsAlive(false);
                socket.ping();
            });
        }, 30000);
    }

    getRoomId(){
        return this.roomId;
    }

    addMember(member: Member){
        this.members.set(member.getId(), member);
    }

    removeMember(memberId: string){
        this.members.delete(memberId);
    }

    getMember(memberId: string){
        return this.members.get(memberId);
    }

    hasMember(memberId: string){
        return this.members.has(memberId);
    }

    publishMessage(message: string, sentBy: Member){
        const from = {name: sentBy.getName(), id: sentBy.getId()};
        const msg = JSON.stringify({type: MessageType.RECEIVE_MESSAGE, data: {message, from}, message: "New message..."})
        this.members.forEach((member: Member) => {
            if(member.getId() !== sentBy.getId()) member.getSocket().send(msg);
        });
    }
}