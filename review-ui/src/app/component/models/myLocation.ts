import { Review } from './review';

export class MyLocation {
    reviewType: string;
    alias: string;
    name: string;
    isClosed: boolean;
    addres: string;
    phoneNumber: string;
    displayNumber: string;
    url: string;
    displayName: string[];
    ratings: number;
    categories: string[];
    photos: string[];
    reviews: Review[];

    constructor(
        reviewType?: string,
        alias?: string,
        name?: string,
        isClosed?: boolean,
        addres?: string,
        phoneNumber?: string,
        displayNumber?: string,
        url?: string,
        displayName?: string[],
        ratings?: number,
        categories?: string[],
        photos?: string[],
        reviews?: Review[]
    ) {
        this.reviewType = reviewType;
        this.alias = alias;
        this.name = name;
        this.isClosed = isClosed;
        this.addres = addres;
        this.phoneNumber = phoneNumber;
        this.displayNumber = displayNumber;
        this.url = url;
        this.displayName = displayName;
        this.ratings = ratings;
        this.categories = categories;
        this.photos = photos;
        this.reviews = reviews;
    }
}
