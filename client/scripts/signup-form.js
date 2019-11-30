import AjaxForm from './ajax-form'

export default function SignupForm() {
	new AjaxForm({
		selector: '.js-signup-form',
		endpoint: '/signup'
	});
}