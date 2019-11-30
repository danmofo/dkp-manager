import AjaxForm from './ajax-form'

export default function AwardDkpForm() {
	new AjaxForm({
		selector: '.js-award-dkp-form',
		endpoint: '/guild-management/award-dkp'
	});
}

