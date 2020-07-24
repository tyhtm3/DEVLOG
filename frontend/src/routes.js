import Dashboard from './components/Dashboard.vue'
import DashboardIndex from './components/modules/dashboard/Index.vue'
import NotFound from './components/modules/dashboard/404.vue'
import WidgetsIndex from './components/modules/widgets/Index.vue'
import UIButtons from './components/modules/ui/Buttons.vue'
import UIGeneral from './components/modules/ui/General.vue'
import UIIcons from './components/modules/ui/Icons.vue'
import UIModals from './components/modules/ui/Modals.vue'
import UISliders from './components/modules/ui/Sliders.vue'
import UITimeline from './components/modules/ui/Timeline.vue'
// Form
import FormRadio from './components/modules/form/FormRadio.vue'
import FormCheckbox from './components/modules/form/FormCheckbox.vue'
import FormInput from './components/modules/form/FormInput.vue'
import FormSelect from './components/modules/form/FormSelect.vue'
import FormCascader from './components/modules/form/FormCascader.vue'
import FormSwitch from './components/modules/form/FormSwitch.vue'
import VueBar from './components/modules/chart/VueBar.vue'
import ChartJs from './components/modules/chart/ChartJs.vue'
import ChartBar from './components/modules/chart/ChartBar.vue'
import ChartPie from './components/modules/chart/ChartPie.vue'
import FormSlider from './components/modules/form/FormSlider.vue'
import FormTimePicker from './components/modules/form/FormTimePicker.vue'
import Mail from './components/modules/Mail.vue'
import DashboardSecond from './components/modules/dashboard/DashboardSecond.vue'
import Chat from './components/modules/Chat.vue'
import Calendar from './components/modules/Calendar.vue'
import ToDo from './components/modules/ToDo.vue'
import BlogList from './components/modules/BlogList.vue'
import BlogDetails from './components/modules/BlogDetails.vue'
import BlogMansory from './components/modules/BlogMansory.vue'
import ShopHome from './components/modules/ShopHome.vue'
import ShopItem from './components/modules/ShopItem.vue'
import BlankPage from './components/modules/common-page/BlankPage.vue'
import InvoicePage from './components/modules/common-page/Invoice.vue'
import Profile from './components/modules/common-page/Profile.vue'
import Login from './components/Login.vue'
import NotFoundSecond from './components/modules/dashboard/500.vue'
import SimpleTable from './components/modules/tables/SimpleTable.vue'
import SelectTable from './components/modules/tables/SelectTable.vue'
import ExpandableTable from './components/modules/tables/ExpandableTable.vue'
import Editor from './components/modules/ui/Editor.vue'
import Upload from './components/modules/ui/Upload.vue'
import Gmap from './components/modules/Gmap.vue'
import ChartMap from './components/modules/chart/ChartMap.vue'
import OpenLayer from './components/modules/OpenLayer.vue'

// devlog
import BlogMain from './components/modules/y_BlogMain.vue'
import BlogDetail from './components/modules/j_BlogDetail.vue'
import DashboardJi from './components/modules/dashboard/ji_Index.vue'
import DashboardJu from './components/modules/dashboard/ju_Index.vue'

// Routes
const routes = [
  {
    path: '/',
    component: Dashboard,
    meta: { requiresAuth: true },
    beforeEnter: (to, from, next) => {
      document.body.className += 'skin-dark sidebar-mini'
      next()
    },
    activate: function () {
      this.$nextTick(function () {
        // => 'DOM loaded and ready'
        alert('test')
      })
    },
    children: [
      {
        path: '',
        name: 'dashboard',
        component: BlogMain
      }, {
        path: '/login',
        name: 'login',
        component: Login
      }, {
        path: '/dashboard',
        name: 'dashboard-first',
        component: DashboardIndex
      }, {
        path: '/dashboard-second',
        name: 'dashboard-second',
        component: DashboardSecond
      }, {
        path: '/chat',
        name: 'chat',
        component: Chat
      }, {
        path: '/calendar',
        name: 'calendar',
        component: Calendar
      }, {
        path: '/todo',
        name: 'todo',
        component: ToDo
      }, {
        path: '/blog-list',
        name: 'blog-list',
        component: BlogList
      }, {
        path: '/blog-details',
        name: 'blog-details',
        component: BlogDetails
      }, {
        path: '/blog-mansory',
        name: 'blog-mansory',
        component: BlogMansory
      }, {
        path: '/shop-home',
        name: 'shop-home',
        component: ShopHome
      }, {
        path: '/shop-item',
        name: 'shop-item',
        component: ShopItem
      }, {
        path: '/invoice',
        name: 'invoice',
        component: InvoicePage
      }, {
        path: '/widgets',
        name: 'widgets',
        component: WidgetsIndex
      }, {
        path: '/mail',
        name: 'mail',
        component: Mail
      }, {
        path: '/blank-page',
        name: 'blank-page',
        component: BlankPage
      }, {
        path: '/profile',
        name: 'profile',
        component: Profile
      }, {
        path: '/404',
        name: '404-eror',
        component: NotFound
      }, {
        path: '/500',
        name: '500',
        component: NotFoundSecond
      }, {
        path: '/simple-table',
        name: 'simple-table',
        component: SimpleTable
      }, {
        path: '/select-table',
        name: 'select-table',
        component: SelectTable
      }, {
        path: '/expandable-table',
        name: 'expandable-table',
        component: ExpandableTable
      }, {
        path: '/ui/buttons',
        name: 'ui-buttons',
        component: UIButtons
      }, {
        path: '/ui/ui-editor',
        name: 'ui-editor',
        component: Editor
      }, {
        path: '/ui/general',
        name: 'ui-general',
        component: UIGeneral
      }, {
        path: '/ui/icons',
        name: 'ui-icons',
        component: UIIcons
      }, {
        path: '/ui/modals',
        name: 'ui-modals',
        component: UIModals
      }, {
        path: '/ui/sliders',
        name: 'ui-sliders',
        component: UISliders
      }, {
        path: '/ui/timeline',
        name: 'ui-timeline',
        component: UITimeline
      }, {
        path: '/form/radio',
        name: 'form-radio',
        component: FormRadio
      }, {
        path: '/form/checkbox',
        name: 'form-checkbox',
        component: FormCheckbox
      }, {
        path: '/form/input',
        name: 'form-input',
        component: FormInput
      }, {
        path: '/form/select',
        name: 'form-select',
        component: FormSelect
      }, {
        path: '/ui/ui-upload',
        name: 'ui-upload',
        component: Upload
      }, {
        path: '/form/cascader',
        name: 'form-cascader',
        component: FormCascader
      }, {
        path: '/chart-pie',
        name: 'chart-pie',
        component: ChartPie
      },
      {
        path: '/form/switch',
        name: 'form-switch',
        component: FormSwitch
      },
      {
        path: '/form/slider',
        name: 'form-slider',
        component: FormSlider
      },
      {
        path: '/form/timepicker',
        name: 'form-timepicker',
        component: FormTimePicker
      },
      {
        path: '/chart/vuebar',
        name: 'chart-vuebar',
        component: VueBar
      },
      {
        path: '/chart/chartjs',
        name: 'chartjs',
        component: ChartJs
      },
      {
        path: '/chart-bar',
        name: 'chart-bar',
        component: ChartBar
      },
      {
        path: '/gmap',
        name: 'gmap',
        component: Gmap
      }, {
        path: '/open-layer',
        name: 'open-layer',
        component: OpenLayer
      }, {
        path: '/chart/chart-map',
        name: 'chart-map',
        component: ChartMap
      },
      {
        path: '/blog-main',
        name: 'blog-main',
        component: BlogMain
      },
      {
        path: '/blog-detail',
        name: 'blog-detail',
        component: BlogDetail
      },
      {
        path: '/dashboard-ji',
        name: 'dashboard-ji',
        component: DashboardJi
      },
      {
        path: '/dashboard-ju',
        name: 'dashboard-ju',
        component: DashboardJu
      },
      {
        path: '*',
        name: '404',
        component: NotFound
      }
    ]

  }
]


export default routes