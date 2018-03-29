import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardDetailsComponent } from './dashboard/dashboarddetails.component';
import { CustomerComponent } from './customer/customer.component';

const routes:Routes=[
    {path:"dashboard",component:DashboardComponent},
    {path:"customer",component:CustomerComponent},
    {path:"dashboarddetail/:id",component:DashboardDetailsComponent}
  ];


  @NgModule({
      imports:[
            RouterModule.forRoot(routes)
        ],
      exports:[
          RouterModule
        ]
  })
  export class AppRoutingModule{

  }
  
  export const routingComponents=[
    DashboardComponent,
    CustomerComponent,
    DashboardDetailsComponent
  ]