import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { MyLocation } from '../models/myLocation';

@Injectable({
  providedIn: 'root'
})
export class InventoryViewService {

  private _reviewList$: BehaviorSubject<MyLocation[]>;

  public selectedCategory: BehaviorSubject<string[]>;
  public selectedType: BehaviorSubject<string[]>;

  public get reviewList$(): BehaviorSubject<MyLocation[]> {
    return this._reviewList$;
  }
  public set reviewList$(value: BehaviorSubject<MyLocation[]>) {
    this._reviewList$ = value;
  }

  constructor(private http: HttpClient) {}
  public get(uri: string, params?: HttpParams): any {
    return params ? this.http.get(uri, {params}) : this.http.get(uri);
  }

  public post(uri: string, data: any, headers?: HttpHeaders): any {
    return this.http.post(uri, data, {headers});
  }

  public getMyReviews(input: string): Observable<any> {
    return this.get('/api/reviews/search', new HttpParams().set('inputText', input));
  }

  public getMyReviewsLatLng(lat: string, lng: string): Observable<any> {
    return this.get('/api/reviews/search', new HttpParams().set('lat', lat).set('lng', lng).set('radius', '300'));
  }

  public getRating(rating: number): string[] {
    const stars = [];
    const full = Math.floor(rating);
    const half = Math.ceil((rating - full) % 1);
    const empty = Math.floor(5 - (full + half));
    for (let i = 0; i < full; i++) {
      stars.push('star fa fa-star');
    }
    for (let i = 0; i < half; i++) {
      stars.push('star fa fa-star-half-o');
    }
    for (let i = 0; i < empty; i++) {
      stars.push('star fa fa-star-o');
    }
    return stars;
  }
}
