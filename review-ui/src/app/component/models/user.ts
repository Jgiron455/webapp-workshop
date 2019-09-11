export class User {
    private id: string;
    private profile_url: string;
    private image_url: string;
    private name: string;

    constructor(
        id?: string,
        profile_url?: string,
        image_url?: string,
        name?: string) {
        this.id = id;
        this.profile_url = profile_url;
        this.image_url = image_url;
        this.name = name;
    }

}
