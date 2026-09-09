import type WebSocket from "ws";

export default class Member{
    private socket: WebSocket;
    private name: string;
    private readonly id: string;
    private roomId: string;
    private isAlive: boolean;
    
    constructor(socket: WebSocket){
        this.socket = socket;
        this.id = this.name = crypto.randomUUID();
        this.roomId = "";
        this.isAlive = true;
    }

    setName(name: string){this.name = name;}
    setRoomId(roomId: string){this.roomId = roomId;}
    setIsAlive(alive: boolean){this.isAlive = alive;}

    getSocket(){ return this.socket; }
    getName(){ return this.name; }
    getId(){ return this.id; }
    getRoomId(){ return this.roomId; }
    getIsAlive() { return this.isAlive; }
}