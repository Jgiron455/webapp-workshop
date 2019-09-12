import { Component, OnInit, Input } from '@angular/core';
import { Review } from '../../models/review';
import { InventoryViewService } from '../inventory-view.service';
// import { InventoryViewService } from '../inventory-view.service';

@Component({
  selector: 'app-review-card',
  templateUrl: './review-card.component.html',
  styleUrls: ['./review-card.component.scss']
})
export class ReviewCardComponent implements OnInit {

  @Input() review: Review;
  constructor(private inventoryService: InventoryViewService) { }

  ngOnInit() {
    // console.log('review: ', this.review);
  }

  public getRatings(rating: number): string[] {
    return this.inventoryService.getRating(rating);
  }

}
