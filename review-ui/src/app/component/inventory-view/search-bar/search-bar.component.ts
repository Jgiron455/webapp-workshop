import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { InventoryViewService } from '../inventory-view.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent implements OnInit {

  @Input() modalInput: any;

  @Output() event = new EventEmitter();
  @Output() selected = new EventEmitter();

  public inputValue: string;
  public categories$: BehaviorSubject<string[]>;
  public type$: BehaviorSubject<string[]>;

  public selectedCategory: string[];
  public selectedType: string[];

  public isModal: boolean;

  public options: any;
  public overlays: any[];

  constructor(public inventoryService: InventoryViewService) { }

  ngOnInit() {
    this.inputValue = '';
    this.isModal = false;
    this.selectedCategory = [];
    this.inventoryService.selectedCategory = new BehaviorSubject<any>([]);
    this.selectedType = [];
    this.inventoryService.selectedType = new BehaviorSubject<any>([]);
    this.setCategory();
    this.setReviewtype();
  }


  public onOpenModal() {
    this.isModal = true;

    this.options = {
      center: { lat: 54.59813783734662, lng: -5.945142838189668 },
      zoom: 12
    };
  }

  public setCategory() {
    const tempArr = [];
    if (this.inventoryService.reviewList$) {
      this.inventoryService.reviewList$.getValue().forEach(location => {
        location.categories.forEach(category => {
          // console.log(tempArr.indexOf(category));
          if (tempArr.indexOf(category) <= 0) {
            tempArr.push(category);
          }
        });
      });

      const arr = this.distinct(tempArr);
      const categoriesList = [];

      arr.forEach(item => {
        const str = item.replace(/_/g, ' ');
        categoriesList.push({ label: `${str.charAt(0).toUpperCase() + str.slice(1)}`, value: `${item}` });
      });
      this.categories$ = new BehaviorSubject<any>(categoriesList);
    }
  }

  public setReviewtype() {
    const tempArr = [];
    if (this.inventoryService.reviewList$) {
      this.inventoryService.reviewList$.getValue().forEach(location => {
        tempArr.push(location.reviewType);
      });
      const arr = this.distinct(tempArr);
      const reviewList = [];

      arr.forEach(item => {
        reviewList.push({ label: `${item.charAt(0).toUpperCase() + item.slice(1)}`, value: `${item}` });
      });
      this.type$ = new BehaviorSubject<any>(reviewList);
    }
  }

  public onSearch(): void {
    this.event.emit(this.inputValue);
    this.inputValue = '';
  }

  public onCategorySelected($event) {
    this.inventoryService.selectedCategory = new BehaviorSubject<any>($event.value);
    this.selected.emit({ label: 'Category', value: $event.value });
  }

  public onReview($event) {
    this.inventoryService.selectedType = new BehaviorSubject<any>($event.value);
    this.selected.emit({ label: 'Review', value: $event.value });
  }

  public handleMapClick($event) {
    this.event.emit($event);
    this.isModal = false;
  }

  public onHide($event) {
    this.isModal = false;
  }

  private distinct(tempArr): string[] {
    return tempArr.filter((item, pos) => {
      return tempArr.indexOf(item) === pos;
    });

  }
}
