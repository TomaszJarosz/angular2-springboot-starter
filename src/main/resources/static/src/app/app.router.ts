import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {AppComponent} from './app.component';
import {BlogComponent} from './blog/blog.component';
import {AboutComponent} from './about/about.component';
import {PortfolioComponent} from './portfolio/portfolio.component';
import {HomeComponent} from "./home/home.component";

export const router: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component:HomeComponent},
  {path: 'blog', component:BlogComponent},
  {path: 'about', component:AboutComponent},
  {path: 'portfolio', component:PortfolioComponent}
]

export const routes:ModuleWithProviders = RouterModule.forRoot(router);

