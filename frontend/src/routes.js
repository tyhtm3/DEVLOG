import DashboardIndex from './References/dashboard/Index.vue'
import WidgetsIndex from './References/widgets/Index.vue'
import UIButtons from './References/ui/Buttons.vue'
import UIGeneral from './References/ui/General.vue'
import UIIcons from './References/ui/Icons.vue'
import UIModals from './References/ui/Modals.vue'
import UISliders from './References/ui/Sliders.vue'
import UITimeline from './References/ui/Timeline.vue'
import FormRadio from './References/form/FormRadio.vue'
import FormCheckbox from './References/form/FormCheckbox.vue'
import FormInput from './References/form/FormInput.vue'
import FormSelect from './References/form/FormSelect.vue'
import FormCascader from './References/form/FormCascader.vue'
import FormSwitch from './References/form/FormSwitch.vue'
import VueBar from './References/chart/VueBar.vue'
import ChartJs from './References/chart/ChartJs.vue'
import ChartBar from './References/chart/ChartBar.vue'
import ChartPie from './References/chart/ChartPie.vue'
import FormSlider from './References/form/FormSlider.vue'
import FormTimePicker from './References/form/FormTimePicker.vue'
import Mail from './References/Mail.vue'
import DashboardSecond from './References/dashboard/DashboardSecond.vue'
import Chat from './References/Chat.vue'
import Calendar from './References/Calendar.vue'
import ToDo from './References/ToDo.vue'
import BlogList from './References/BlogList.vue'
import BlogDetails from './References/BlogDetails.vue'
import BlogMansory from './References/BlogMansory.vue'
import ShopHome from './References/ShopHome.vue'
import ShopItem from './References/ShopItem.vue'
import BlankPage from './References/common-page/BlankPage.vue'
import InvoicePage from './References/common-page/Invoice.vue'
import Profile from './References/common-page/Profile.vue'
import SimpleTable from './References/tables/SimpleTable.vue'
import SelectTable from './References/tables/SelectTable.vue'
import ExpandableTable from './References/tables/ExpandableTable.vue'
import Editor from './References/ui/Editor.vue'
import Upload from './References/ui/Upload.vue'
import Gmap from './References/Gmap.vue'
import ChartMap from './References/chart/ChartMap.vue'
import OpenLayer from './References/OpenLayer.vue'

// devlog
import Devlog from './pages/Devlog.vue'
import Main from './pages/Main.vue'
import Naver from './pages/Naver.vue'
import Blog from './pages/Blog.vue'
import SignUp from './pages/SignUp.vue'
import MyInfo from './pages/MyInfo.vue'
import FindPassword from './pages/FindPassword'
import WritePost from './pages/WritePost.vue'
import ProjectDetail from './pages/ProjectDetail.vue'
import PostDetail from './pages/PostDetail.vue'
import NotFound from './pages/error/404.vue'
import NotFoundSecond from './pages/error/500.vue'
import PostUpdate from './pages/PostUpdate.vue'
import ProjectUpdate from './pages/ProjectUpdate.vue'
import PortfolioUpdate from './pages/PortfolioUpdate.vue'
import Template01 from './pages/Template01.vue'
import Template02 from './pages/Template02.vue'
import Template03 from './pages/Template03.vue'
import Template05 from './pages/Template05.vue'
import store from './store'

// Routes
const routes = [
  {
    path: '/',
    component: Devlog,
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
        name: 'main',
        component: Main
      },
      {
        path: '/naver/*',
        name: 'naver',
        component: Naver
      },
      {
        path: '/blog/:id',
        name: 'blog',
        component: Blog
      },
      {
        path: '/blog/post/:seq',
        name: 'post-detail',
        component: PostDetail,
        beforeEnter: (to, from, next) => {
          store.commit('setPreviousUrl',from.path)
          next()
        }
      },
      {
        path: '/blog/project/:seq',
        name: 'project-detail',
        component: ProjectDetail,
        beforeEnter: (to, from, next) => {
          store.commit('setPreviousUrl',from.path)
          next()
        }
      },
      {
        path: '/signup',
        name: 'sign-up',
        component: SignUp
      },
      {
        path: '/findpw',
        name: 'find-password',
        component: FindPassword
      },
      {
        path: '/myinfo',
        name: 'my-info',
        component: MyInfo
      },
      {
        path: '/writepost',
        name: 'write-post',
        component: WritePost
      },
      {
        path: '/404',
        name: '404-eror',
        component: NotFound
      },
      {
        path: '/500',
        name: '500',
        component: NotFoundSecond
      },
      {
        path: '/blog/post-update/:seq',
        name: 'post-update',
        component: PostUpdate
      },
      {
        path: '/blog/project-update/:seq',
        name: 'project-update',
        component: ProjectUpdate
      },
      {
        path: '/blog/portfolio-update/:seq',
        name: 'portfolio-update',
        component: PortfolioUpdate
      },
      {
        path: '/blog/portfolio1/:seq',
        name: 'template01',
        component: Template01
      },
      {
        path: '/blog/portfolio2/:seq',
        name: 'template02',
        component: Template02
      },
      {
        path: '/blog/portfolio3/:seq',
        name: 'template03',
        component: Template03
      },
      {
        path: '/blog/portfolio5/:seq',
        name: 'template05',
        component: Template05
      },

      ///////////////////////////
      ///////////////////////////
      ///////////////////////////
      ///////////////////////////
      ///////////////////////////
      ///////////////////////////



      {
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
        path: '*',
        name: '404',
        component: NotFound
      },
    ]

  }
]


export default routes