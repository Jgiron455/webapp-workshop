export class User {
    private id: string;
    private profileUrl: string;
    private imageUrl: string;
    private name: string;

    constructor(
        id?: string,
        profileUrl?: string,
        imageUrl?: string,
        name?: string) {
        this.id = id;
        this.profileUrl = profileUrl;
        this.imageUrl = imageUrl;
        this.name = name;
    }

}
