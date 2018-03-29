import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router'

@Component({
  selector: 'dashboard-details-component',
  template: `<h1>yo have selected :{{listid}}</h1>
            <a (click)="goPrevious()">Previous</a><a (click)="goNext()">Next</a>
            <button (click)="gotoDashboard()">back</button>`
})
export class DashboardDetailsComponent implements OnInit{
 
    public listid;
    public prevId;
    public nextId;

    constructor(private route:ActivatedRoute, private router:Router){

    }
/*     ngOnInit(){
        let id=this.route.snapshot.params.id;
        this.listid=id;
    } */

    ngOnInit(){
        this.route.params.subscribe((params:Params)=>{
            let id=parseInt(params.id);
            this.listid=id;
        })
    }
    goPrevious(){
        let prevId=this.listid - 1;
        this.router.navigate(['/dashboarddetail',prevId]);
    }
    goNext(){
        let nextId=this.listid +1;
        this.router.navigate(['/dashboarddetail',nextId]);
    }
    gotoDashboard(){
        let selectedId=this.listid;
        this.router.navigate(['/dashboard',{id:selectedId}]);
    }
}
