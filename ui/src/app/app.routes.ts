import {Routes} from '@angular/router';
import {BoardComponent} from "./board/board.component";

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'home'
  },
  {
    path: 'home',
    component: BoardComponent
  }

];
