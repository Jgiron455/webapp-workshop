import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SearchBarComponent } from './component/inventory-view/search-bar/search-bar.component';
import { ReviewCardComponent } from './component/inventory-view/review-card/review-card.component';

import { MatMenuModule } from '@angular/material/menu';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { FieldsetModule } from 'primeng/fieldset';

import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { CarouselModule } from 'primeng/carousel';
import { AccordionModule } from 'primeng/accordion';
import { PaginatorModule } from 'primeng/paginator';
import { MultiSelectModule } from 'primeng/multiselect';
import { DialogModule } from 'primeng/dialog';
import { GMapModule } from 'primeng/gmap';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';

import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { CardModule } from 'primeng/card';
import { CommonModule } from '@angular/common';
import { InventoryViewService } from './component/inventory-view/inventory-view.service';
import { InventoryViewComponent } from './component/inventory-view/inventory-view.component';

import { AgmCoreModule } from '@agm/core';

@NgModule({
  declarations: [
    AppComponent,
    InventoryViewComponent,
    SearchBarComponent,
    ReviewCardComponent
  ],
  imports: [
    AccordionModule,
    AngularFontAwesomeModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyA3PaFq-hiMksMbtDXmFw5QM178cgeafpw'
    }),
    BrowserAnimationsModule,
    BrowserModule,
    CardModule,
    CarouselModule,
    CommonModule,
    DialogModule,
    FormsModule,
    FieldsetModule,
    GMapModule,
    HttpClientModule,
    MatMenuModule,
    MatInputModule,
    MatProgressSpinnerModule,
    MessagesModule,
    MessageModule,
    MultiSelectModule,
    PaginatorModule,
    ProgressSpinnerModule
  ],
  providers: [InventoryViewService],
  bootstrap: [AppComponent]
})
export class AppModule { }
