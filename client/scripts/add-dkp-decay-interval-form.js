import AjaxForm from './ajax-form'

export default function AddDkpDecayIntervalForm() {
	new AjaxForm({
		selector: '.js-add-dkp-decay-interval-form',
		endpoint: '/guild-management/add-dkp-decay-interval'
	});
}