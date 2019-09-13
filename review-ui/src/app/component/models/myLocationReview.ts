import { MyLocation } from './myLocation';

export class MyLocationReview {
    requestQuery: string;
    myLocationDtoList: MyLocation[];

    constructor(requestQuery?: string, myLocationDtoList?: MyLocation[]) {
        this.requestQuery = requestQuery;
        this.myLocationDtoList = myLocationDtoList;
    }
}
