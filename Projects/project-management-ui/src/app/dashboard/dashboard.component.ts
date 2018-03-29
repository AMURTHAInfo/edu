import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'dashboard-component',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent {
    constructor(private router:Router){}
    dashboardList = [{id:1,name:"project"},
    {id:2,name:"Task"},
    {id:3,name:"Bug"},
    {id:4,name:"CR"}];

    onSelect(list){
        this.router.navigate(['/dashboarddetail',list.id]);
    }
}
