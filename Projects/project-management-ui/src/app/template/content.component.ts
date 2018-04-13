import { Component, ViewChild, ElementRef, AfterViewInit } from '@angular/core';

@Component({
  selector: 'content-component',
  templateUrl: './content.component.html'
})
export class ContentComponent {

  @ViewChild('wrapper') wrapper: ElementRef;

  navBarOpen:boolean=false;

  toggleSideNav(){  
    this.navBarOpen=!this.navBarOpen;
    console.log(this.wrapper.nativeElement) ;
    this.wrapper.nativeElement.toggleClass("toggled");
  }
}
