import AjaxForm from './ajax-form'

export default function LoginForm() {
	new AjaxForm({
		selector: '.js-login-form',
		endpoint: '/login'
	});	
}