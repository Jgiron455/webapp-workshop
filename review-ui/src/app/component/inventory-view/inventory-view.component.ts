import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { MyLocationReview } from '../models/myLocationReview';
import { BehaviorSubject } from 'rxjs';
import { MyLocation } from '../models/myLocation';
import { InventoryViewService } from './inventory-view.service';

@Component({
  selector: 'app-inventory-view',
  templateUrl: './inventory-view.component.html',
  styleUrls: ['./inventory-view.component.scss']
})

export class InventoryViewComponent implements OnInit {

  @Input() event: string;

  public isSearched: boolean;
  public isLoading: boolean;

  public paginatorSize = 10;
  public paginatorPageSelected = 1;
  public paginatorLength = 0;

  public myLocationReview: MyLocationReview;
  public myLocationDtoList: MyLocation[];

  public msgs: any[];

  constructor(private inventoryService: InventoryViewService) { }

  ngOnInit() {
    this.isLoading = false;
    // this.onEvent('belfast');
  }

  public onEvent($event): void {
    // console.log('Event', $event);
    if ($event && $event !== '') {
      this.isSearched = false;
      this.isLoading = true;
      if ($event.latLng) {
        this.inventoryService.getMyReviewsLatLng($event.latLng.lat(), $event.latLng.lng()).subscribe((res: MyLocationReview) => {
          this.myLocationReview = res;
          this.processSearch(res);
        },
          err => {
            this.onError(err.message);
            this.isSearched = true;
            this.isLoading = false;
          }, () => console.log('HTTP request completed.'));
      } else {
        // console.log('Event', $event);
        this.inventoryService.getMyReviews($event.toLowerCase()).subscribe((res: MyLocationReview) => {
          this.myLocationReview = res;
          this.processSearch(res);
        },
          err => {
            this.onError(err.message);
            this.isSearched = true;
            this.isLoading = false;
          }, () => console.log('HTTP request completed.'));
      }
    }
  }

  public onSearchClass(): string {
    return this.isSearched ? 'container-fluid center-top fadeIn' : 'container-fluid center fadeIn';
  }

  public getRatings(rating: number): string[] {
    return this.inventoryService.getRating(rating);
  }

  public paginate($event) {
    // console.log('Paginate: ', $event);
    const newArr = this.myLocationDtoList.slice($event.first, $event.first + $event.rows);
    // console.log('New Array: ', newArr);
    this.inventoryService.reviewList$.next(newArr);
  }

  public getCurrentPage(array: any[], rangefirst: number, rangeSecond: number): any {
    array.splice(rangefirst, rangeSecond);
    return array;
  }

  public onSelected($event) {
    // console.log($event);
    if ($event && $event.value.length > 0) {
      this.inventoryService.reviewList$.next(this.myLocationDtoList);
      this.setByCategory();
      this.setByReview();
    } else {
      this.inventoryService.reviewList$.next(this.myLocationDtoList.slice(0, this.paginatorSize));
    }
  }

  public setByCategory() {
    if (this.inventoryService.selectedCategory.getValue() && this.inventoryService.selectedCategory.getValue().length > 0) {
      const tempArr = this.inventoryService.reviewList$.getValue().filter(location =>
        location.categories.some(elem =>
          this.inventoryService.selectedCategory.getValue().indexOf(elem) > -1));
      this.inventoryService.reviewList$.next(tempArr);
    }
  }

  public setByReview() {
    if (this.inventoryService.selectedType.getValue() && this.inventoryService.selectedType.getValue().length > 0) {
      const tempArr = this.inventoryService.reviewList$.getValue().filter(location =>
        this.inventoryService.selectedType.getValue().indexOf(location.reviewType) > -1);
      this.inventoryService.reviewList$.next(tempArr);
    }
  }

  private onError(msg: string) {
    this.msgs = [];
    // console.log(msg);
    this.msgs.push({ severity: 'error', summary: 'Sorry it failed', detail: `${msg}` });
  }

  private processSearch(response: MyLocationReview): void {
    if (this.myLocationReview) {
      this.myLocationDtoList = response.myLocationDtoList.filter(location => location.reviews.length > 0);
      this.paginatorLength = this.myLocationDtoList.length;
      this.inventoryService.reviewList$ = new BehaviorSubject(this.myLocationDtoList.slice(0, this.paginatorSize));
      this.isSearched = true;
      this.isLoading = false;
    }
  }
}
