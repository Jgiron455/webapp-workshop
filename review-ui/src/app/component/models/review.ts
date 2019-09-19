import { User } from './user';

export class Review {
     id: string;
     name: string;
     rating: number;
     user: User;
     text: string;
     created: string;

    constructor(
        id?: string,
        name?: string,
        rating?: number,
        user?: User,
        text?: string,
        created?: string) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.user = user;
        this.text = text;
        this.created = created;
    }
}
