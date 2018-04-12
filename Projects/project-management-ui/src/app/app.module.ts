import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router'

import { AppComponent } from './app.component';
import { HeaderComponent } from './template/header.component'
import { ContentComponent } from './template/content.component'
import { FooterComponent } from './template/footer.component'
import { LayoutComponent } from './template/layout.component'
import { AppRoutingModule } from './app.router.module'
import { routingComponents } from './app.router.module'

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ContentComponent,
    FooterComponent,
    LayoutComponent,
    routingComponents
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

