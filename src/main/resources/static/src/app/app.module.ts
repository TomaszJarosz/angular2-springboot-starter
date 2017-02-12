import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { CarouselComponent } from './parts/carousel/carousel.component';
import { NavbarComponent } from './parts/navbar/navbar.component';
import { ArticulesComponent } from './parts/articules/articules.component';
import { PortfolioComponent } from './parts/portfolio/portfolio.component';
import { FooterComponent } from './parts/footer/footer.component';
import { CallToActionComponent } from './parts/call-to-action/call-to-action.component';
import { InfoSectionComponent } from './parts/info-section/info-section.component';

import { CarouselModule } from 'ng2-bootstrap/carousel';

@NgModule({
  declarations: [
    AppComponent,
    CarouselComponent,
    NavbarComponent,
    ArticulesComponent,
    PortfolioComponent,
    FooterComponent,
    CallToActionComponent,
    InfoSectionComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    CarouselModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
