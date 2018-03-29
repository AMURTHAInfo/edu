import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router'

@Component({
  selector: 'dashboard-details-component',
  template: '<h1>yo have selected :{{listid}}</h1>'
})
export class DashboardDetailsComponent implements OnInit{
 
    public listid;
    constructor(private route:ActivatedRoute){

    }
    ngOnInit(){
        let id=this.route.snapshot.params.id;
        this.listid=id;
    }
}
