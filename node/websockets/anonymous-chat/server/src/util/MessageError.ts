export default class MessageError extends Error{
    private type: string;
    constructor(type: string, message: string){
        super(message);
        this.type = type;
        this.message = message;
    }
    getType(){
        return this.type;
    }
    getMessage(){
        return this.message;
    }
}