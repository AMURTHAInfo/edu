import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'dashboard-component',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit{
    constructor(private route:ActivatedRoute, private router:Router){}

    public selectedid;
    ngOnInit(){
        this.route.params.subscribe((params:Params)=>{
            let id=params.id;
            this.selectedid=id;
        })
    }

    dashboardList = [{id:1,name:"project"},
    {id:2,name:"Task"},
    {id:3,name:"Bug"},
    {id:4,name:"CR"}];

    onSelect(list){
        this.router.navigate(['/dashboarddetail',list.id]);
    }

    isSelected(list){
        return list.id == this.selectedid;
    }
}
