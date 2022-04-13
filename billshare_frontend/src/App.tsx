import { Redirect, Route } from 'react-router-dom';
import { IonApp, IonRouterOutlet, setupIonicReact } from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage'
import SignUpPage from './pages/SignUpPage';

/* Core CSS required for Ionic components to work properly */
import '@ionic/react/css/core.css';

/* Basic CSS for apps built with Ionic */
import '@ionic/react/css/normalize.css';
import '@ionic/react/css/structure.css';
import '@ionic/react/css/typography.css';

/* Optional CSS utils that can be commented out */
import '@ionic/react/css/padding.css';
import '@ionic/react/css/float-elements.css';
import '@ionic/react/css/text-alignment.css';
import '@ionic/react/css/text-transformation.css';
import '@ionic/react/css/flex-utils.css';
import '@ionic/react/css/display.css';

/* Theme variables */
import './theme/variables.css';




import CreateBillPage from './pages/CreateBillPage';
import ProfilePage from './pages/ProfilePage';
// @ts-ignore
import BillDetailPage from './pages/BillDetailPage';



setupIonicReact();

const App: React.FC = () => (
  <IonApp>
    <IonReactRouter>
      <IonRouterOutlet>
        <Route path='/' exact render={()=><Redirect to="/login"></Redirect>}>
          
        </Route>
        <Route path='/login' exact component={LoginPage}>
          {/* <LoginPage /> */}
        </Route>
        <Route path='/home' exact component={HomePage}>
          
        </Route>
        <Route path='/signup'>
          <SignUpPage />
        </Route>
        <Route path='/create_bill' exact component={CreateBillPage}>
          {/* <CreateBillPage /> */}
        </Route>
        <Route path='/profile/:email' exact component={ProfilePage}>
        </Route>
        <Route path='/bills/:billId'>
          <BillDetailPage role="owner"/>
        </Route>
        <Route path='/debts/:billId/:status'>
          <BillDetailPage role="debtor"/>
        </Route>
        {/* <Route path='/test'>
          <ManangeBillButtonComponent bid={92}/>
        </Route> */}

      </IonRouterOutlet>
    </IonReactRouter>
  </IonApp>
);

export default App;
