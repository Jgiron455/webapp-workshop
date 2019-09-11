import { User } from './user';

export class Review {
    private id: string;
    private rating: number;
    private text: string;
    private created: string;

    constructor(
        id?: string,
        rating?: number,
        user?: User,
        text?: string,
        created?: string) {
        this.id = id;
        this.rating = rating;
        this.text = text;
        this.created = created;
    }
}
