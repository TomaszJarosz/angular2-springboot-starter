import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {CarouselComponent} from './parts/carousel/carousel.component';
import {NavbarComponent} from './parts/navbar/navbar.component';
import {ArticulesComponent} from './parts/articules/articules.component';
import {FooterComponent} from './parts/footer/footer.component';
import {CallToActionComponent} from './parts/call-to-action/call-to-action.component';
import {InfoSectionComponent} from './parts/info-section/info-section.component';
import {routes} from './app.router'

import {CarouselModule} from 'ng2-bootstrap/carousel';
import {AboutComponent} from './about/about.component';
import {BlogComponent} from './blog/blog.component';
import {PortfolioComponent} from './portfolio/portfolio.component';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';

@NgModule({
  declarations: [
    AppComponent,
    CarouselComponent,
    NavbarComponent,
    ArticulesComponent,
    PortfolioComponent,
    FooterComponent,
    CallToActionComponent,
    InfoSectionComponent,
    AboutComponent,
    BlogComponent,
    PortfolioComponent,
    HomeComponent,
    ContactComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    CarouselModule.forRoot(),
    routes
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
